package com.lemon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.lemon.entity.VideoEntity;

/**
 * PageVideoRepository
 *
 * @author sjp
 * @date 2019/5/23
 */
@Repository
public interface VideoPageRepository extends PagingAndSortingRepository<VideoEntity, String> {
}
