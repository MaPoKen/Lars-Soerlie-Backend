package no.larssorlie.models.mappers;

import no.larssorlie.models.domain.Experience;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;
import no.larssorlie.models.dto.SkillDTO;

import java.util.stream.Collectors;

public class ExperienceMapper {
    public static ExperienceDTO toDTO(Experience experience) {
        return new ExperienceDTO()
                .toBuilder()
                .id(experience.getId())
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillDTO::toDto).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }

    public static Experience toModel(ExperienceDTO experience){
        return new Experience()
                .toBuilder()
                .id(experience.getId())
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillDTO::toModel).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toModel).collect(Collectors.toSet()))
                .build();
    }

    public static Experience toModel(NewExperienceDTO experience){
        return new Experience()
                .toBuilder()
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillDTO::toModel).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toModel).collect(Collectors.toSet()))
                .build();
    }
}
