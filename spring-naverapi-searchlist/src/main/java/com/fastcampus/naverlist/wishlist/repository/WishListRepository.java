package com.fastcampus.naverlist.wishlist.repository;

import com.fastcampus.naverlist.db.MemoryDbRepositoryAbstract;
import com.fastcampus.naverlist.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
