package me.project.referenceservice.repository;

import me.project.referenceservice.entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
