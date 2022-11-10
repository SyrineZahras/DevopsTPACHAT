package com.esprit.examen.services;

import java.util.ArrayList;
import java.util.List;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategorieProduitServiceImplTest {
    @Mock
    CategorieProduitRepository categorieRepository2;
    @InjectMocks
    CategorieProduitServiceImpl categorieService;

    CategorieProduit prod1 = new CategorieProduit(2L,"f-552221","Table");

    Long getId()
    {
        for (CategorieProduit cat: categorieRepository2.findAll()) {
            return cat.getIdCategorieProduit();
        }
        return 0L;
    }
    @Test
    @Order(0)
    void TestaddCategorieProduit() {
        CategorieProduit categ = new CategorieProduit();
        List<CategorieProduit> LCategorie = new ArrayList<>();
        for (Long i=1L;i<=10L;i++) {
            categ.setIdCategorieProduit(i);
            categ.setLibelleCategorie("Laptop");
            categ.setCodeCategorie("d-62211");

            CategorieProduit ca=categorieRepository2.save(categ);
            LCategorie.add(ca);
        }
        assertEquals(10,LCategorie.size());
    }
    @Test
    @Order(3)
    void TestdeleteAllCategorieProduit() {
        categorieRepository2.deleteAll();
        assertEquals(0,categorieRepository2.findAll().spliterator().estimateSize());
    }
    @Test
    @Order(2)
    void TestretrieveCategorirProduit() {
        Mockito.when(categorieRepository2.findById(Mockito.anyLong())).thenReturn(Optional.of(prod1));

        Mockito.when(categorieRepository2.findById(Mockito.anyLong())).thenReturn(Optional.of(prod1))
        ;
        CategorieProduit op = categorieService.retrieveCategorieProduit(2L);
        Assertions.assertNotNull(op);


    }
    @Test
    @Order(4)
    void TestgetAllCategorieProduit(){
        Iterable<CategorieProduit> Lcateg = categorieRepository2.findAll();
        Assertions.assertNotNull(Lcateg);
    }
}