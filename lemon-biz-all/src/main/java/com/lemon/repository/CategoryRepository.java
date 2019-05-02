package com.lemon.repository;

import com.lemon.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sjp 2019/1/10
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

	/**
	 * 根据父类id，查询子集分类
	 * 
	 * @param parentId 父级id
	 * @param display 父级id
	 * @return List<CategoryEntity>
	 */
	List<CategoryEntity> findAllByParentIdAndDisplay(long parentId, short display);

}
