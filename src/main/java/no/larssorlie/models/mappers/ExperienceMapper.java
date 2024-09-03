package no.larssorlie.models.mappers;

import no.larssorlie.models.domain.Experience;
import no.larssorlie.models.dto.ExperienceDTO;
import no.larssorlie.models.dto.NewExperienceDTO;

import java.util.stream.Collectors;

public class ExperienceMapper {
    public static ExperienceDTO toDTO(Experience experience) {
        return new ExperienceDTO()
                .toBuilder()
                .id(experience.getId())
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillMapper::toDTO).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }

    public static Experience toModel(ExperienceDTO experience){
        return new Experience()
                .toBuilder()
                .id(experience.getId())
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillMapper::toModel).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toModel).collect(Collectors.toSet()))
                .build();
    }

    public static Experience toModel(NewExperienceDTO experience){
        return new Experience()
                .toBuilder()
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillMapper::toModel).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toModel).collect(Collectors.toSet()))
                .build();
    }

    public static Experience toModel(NewExperienceDTO experience, Long id){
        return new Experience()
                .toBuilder()
                .id(id)
                .title(experience.getTitle())
                .description(experience.getDescription())
                .skills(experience.getSkills().stream().map(SkillMapper::toModel).collect(Collectors.toSet()))
                .projects(experience.getProjects().stream().map(ProjectMapper::toModel).collect(Collectors.toSet()))
                .build();
    }
}
