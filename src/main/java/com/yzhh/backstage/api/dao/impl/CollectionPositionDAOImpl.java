package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ICollectionPositionDAO;
import com.yzhh.backstage.api.entity.CollectionPosition;
import com.yzhh.backstage.api.entity.CollectionPositionExample;

@Repository("collectionPositionDAO")
public class CollectionPositionDAOImpl extends DAOImpl<CollectionPosition, CollectionPositionExample> implements ICollectionPositionDAO {

}
