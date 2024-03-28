package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.libraryMembers.LibraryMemberImportDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static softuni.exam.util.Messages.INVALID_IMPORT_MESSAGE_FORMAT;
import static softuni.exam.util.Messages.VALID_IMPORT_MESSAGE_FORMAT;
import static softuni.exam.util.Paths.LIBRARY_MEMBER_JSON_IMPORT_PATH;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtils validationUtils;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validationUtils) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(LIBRARY_MEMBER_JSON_IMPORT_PATH);
    }

    @Override
    public String importLibraryMembers() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readLibraryMembersFileContent(), LibraryMemberImportDto[].class))
                .forEach(libraryMemberDto -> {
                    if (this.validationUtils.isValid(libraryMemberDto)
                            && this.libraryMemberRepository.findByPhoneNumber(libraryMemberDto.getPhoneNumber()).isEmpty()) {

                        this.libraryMemberRepository.saveAndFlush(modelMapper.map(libraryMemberDto, LibraryMember.class));

                        stringBuilder.append(String.format(VALID_IMPORT_MESSAGE_FORMAT,
                                "library member",
                                libraryMemberDto.getFirstName(),
                                libraryMemberDto.getLastName()));

                        return;
                    }

                    stringBuilder.append(String.format(INVALID_IMPORT_MESSAGE_FORMAT, "library member"));
                });

        return stringBuilder.toString().trim();
    }
}
