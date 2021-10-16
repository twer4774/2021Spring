package com.fastcampus.naverlist.wishlist.repository;

import com.fastcampus.naverlist.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        return WishListEntity.builder()
                .title("title")
                .category("category")
                .address("address")
                .roadAddress("readAddress")
                .homePageLink("")
                .imageLink("")
                .isVisit(false)
                .visitCount(0)
                .lastVisitDate(null)
                .build();
    }

    @Test
    public void saveTest(){
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        assertNotNull(expected);
        assertEquals(1, expected.getIndex());
    }

    @Test
    public void updateTest(){
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        expected.setTitle("update test");
        var saveEntity = wishListRepository.save(expected);

        assertEquals("update test", saveEntity.getTitle());
        assertEquals(1, wishListRepository.findAll().size());
    }

    @Test
    public void findByIdTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.findById(1);

        assertEquals(true, expected.isPresent());
        assertEquals(1, expected.get().getIndex());
    }

    @Test
    public void deleteTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        wishListRepository.deleteById(1);

        int count = wishListRepository.findAll().size();

        assertEquals(0, count);
    }

    @Test
    public void listAllTest(){
        var wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);

        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        int count = wishListRepository.findAll().size();
        assertEquals(2, count);
    }
}