package com.job.watchlist.repository;

import com.job.watchlist.entity.WishListJob;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWishListRepository extends MongoRepository<WishListJob,Long> {
    List<WishListJob> findByUsername(String username);

    Optional<WishListJob> findByUsernameAndExternalServerId(String username, long externalServerId);

}
