package com.api.sitiepro.repository;

import com.api.sitiepro.entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
}
