package com.taotao.manage.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taotao.manage.mapper.ContentCategoryMapper;
import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContentCategoryServiceImp extends BaseServiceImp<ContentCategory> implements ContentCategoryService {
	
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public ContentCategory saveContentCategory(ContentCategory contentCategory) {
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		saveSelective(contentCategory);
		
		//更新父节点
		ContentCategory parent = queryById(contentCategory.getParentId());
		if(!parent.getIsParent()) {
			parent.setIsParent(true);
			parent.setUpdated(new Date());
			updateSelective(parent);
		}
		
		return contentCategory;
	}

	@Override
	public void deleteContentCategory(ContentCategory contentCategory) {
			List<Long> ids = new ArrayList<>();
			getIds(ids, contentCategory);
			ids.add(contentCategory.getId());
			this.deleteByIds(ids.toArray(new Long[] {}));
			
			//更新父类
			ContentCategory siblings = new ContentCategory();
			siblings.setParentId(contentCategory.getParentId());
			Integer result = queryCountByWhere(siblings);
			if(result==0) {
				ContentCategory parent = new ContentCategory();
				parent.setId(contentCategory.getParentId());
				parent.setIsParent(false);
				parent.setUpdated(new Date());
				updateSelective(parent);
			}
	}
	
	public void getIds(List<Long> list,ContentCategory contentCategory) {
		ContentCategory contentCategorysub = new ContentCategory();
		contentCategorysub.setParentId(contentCategory.getId());
		List<ContentCategory> subList = this.queryListByWhere(contentCategorysub);
		if(subList!=null&&subList.size()>0) {
			for (ContentCategory cc : subList) {
				list.add(cc.getId());
				getIds(list,cc);
			}
		}
		
	}
}
