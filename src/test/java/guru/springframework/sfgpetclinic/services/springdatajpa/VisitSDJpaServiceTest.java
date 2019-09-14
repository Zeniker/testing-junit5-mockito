package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    private Visit visit;

    @BeforeEach
    void setUp(){
        visit = new Visit();
    }

    @Test
    void findAll() {
        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        visits.add(new Visit());

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> allVisits = service.findAll();

        assertThat(allVisits).isNotNull().hasSize(2);

        verify(visitRepository).findAll();

    }

    @Test
    void findById() {
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        assertThat(foundVisit).isNotNull();

        verify(visitRepository).findById(1L);
    }

    @Test
    void save() {
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = service.save(visit);

        assertThat(savedVisit).isNotNull();

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(visit);

        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepository).deleteById(1L);
    }
}