package org.openiam.idm.srvc.cat.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openiam.idm.srvc.cat.dto.Category;


@WebService(endpointInterface = "org.openiam.idm.srvc.cat.service.CategoryDataService", 
		targetNamespace = "urn:idm.openiam.org/srvc/cat/service", 
		portName = "CategoryDataWebServicePort", 
		serviceName = "CategoryDataWebService")
public class CategoryDataServiceImpl implements CategoryDataService {

	
	CategoryDAO categoryDao;
	CategoryLanguageDAO categoryLanguageDao;
	
	private static final Log log = LogFactory.getLog(CategoryDataServiceImpl.class);

	
	public void addCategory(Category cat) {
		if (cat == null) {
			throw (new NullPointerException("Category object is null") );
		}

		categoryDao.add(cat);

	}

	public Category[] getAllCategories(boolean nested) {
		
		Category[] categoryAry;
		List<Category> catList = null;
		
		if (!nested) {
			catList = categoryDao.findRootCategories();
		}else {
			catList = getRecursiveCategories(null, catList);
		}
		
		if (catList == null || catList.isEmpty())
			return null;
		categoryAry = new Category[catList.size()];
		catList.toArray(categoryAry);
		return categoryAry;
	}
	private List<Category> getRecursiveCategories(String parentCategoryId, List<Category> catList) {
		
		if (parentCategoryId == null) {
			catList = categoryDao.findRootCategories();
		}else {
			catList = categoryDao.findChildCategories(parentCategoryId);
		}
		if (catList == null || catList.isEmpty())
			return null;
		
		int size = catList.size();
		for (int i=0; i<size; i++) {
			Category cat = catList.get(i);
			// check for child categories
			List<Category> subCat = getRecursiveCategories(cat.getCategoryId(), catList);
			if (subCat != null && !subCat.isEmpty()) {
				int subCatSize = subCat.size();
				Category[] subCatAry = new Category[subCatSize];
				subCat.toArray(subCatAry);
				cat.setChildCategories(subCatAry);
			}
		}
		
		return catList;
	}




	
	
	public Category getCategory(String categoryId) {
		if (categoryId == null) {
			throw (new NullPointerException("CategoryId is null") );
		}

		return categoryDao.findById(categoryId);
		
	}

	public Category[] getChildCategories(String categoryId, boolean nested) {
		Category[] categoryAry;
		List<Category> catList = null;
		
		if (!nested) {
			catList = categoryDao.findChildCategories(categoryId);
		}else {
			catList = getRecursiveCategories(categoryId, catList);
		}
		
		
		if (catList == null || catList.isEmpty())
			return null;
		categoryAry = new Category[catList.size()];
		catList.toArray(categoryAry);
		return categoryAry;

	
	}

	public int removeCategory(String categoryId, boolean nested) {
		if (categoryId == null) {
			throw (new NullPointerException("CategoryId is null") );
		}
		
		if (!nested) {
			Category parentCat = new Category();
			parentCat.setCategoryId(categoryId);
			categoryDao.remove(parentCat);
			return 1;
		}
		

		StringBuffer catIdBuf = new StringBuffer();
		List<Category> catList = new ArrayList<Category>();
	
		catList = categoryDao.findChildCategories(categoryId);

		if (catList == null || catList.isEmpty()) {
			// nothing to delete
			return 0;
		}
	
		int size = catList.size();
		for (int i = 0; i < size; i++) {
			Category cat = catList.get(i);
			if (catIdBuf.length() > 0) {
				catIdBuf.append(",");
			}
			catIdBuf.append(" '" + cat.getCategoryId() + "' ");

			String catStr = getRecursiveCatId(cat.getCategoryId(), catList);
			if (catStr != null) {
				if (catIdBuf.length() > 0) {
					catIdBuf.append(",");
				}
				catIdBuf.append(catStr);
			}

		}		
		
		String catIdStr = catIdBuf.toString();
		int count =  categoryDao.removeGroupList(catIdStr);
		Category parentCat = new Category();
		parentCat.setCategoryId(categoryId);
		categoryDao.remove(parentCat);
		return count++;

	}

	/**
	 * Recursively get that list of categories.
	 * @param parentGroupId
	 * @param groupList
	 * @return
	 */
	private String getRecursiveCatId(String parentCatId,List<Category> categoryList) {
		StringBuffer catIdBuf = new StringBuffer();

		categoryList = categoryDao.findChildCategories(parentCatId);
		if (categoryList == null || categoryList.isEmpty()) {
			return null;
		}
		int size = categoryList.size();
		for (int i = 0; i < size; i++) {
			Category cat = categoryList.get(i);
			if (catIdBuf.length() > 0) {
				catIdBuf.append(",");
			}
			catIdBuf.append(" '" + cat.getCategoryId() + "' ");

			// check for child group

			String catStr = getRecursiveCatId(cat.getCategoryId(), categoryList);
			if (catStr != null) {
				if (catIdBuf.length() > 0) {
					catIdBuf.append(",");
				}
				catIdBuf.append(catStr);
			}
			log.debug("Category ids after = " + catIdBuf.toString());
		}
		return catIdBuf.toString();
	}
	
	
	public void updateCategory(Category cat) {
		if (cat == null) {
			throw (new NullPointerException("Category object is null") );
		}
		categoryDao.update(cat);
	}

	public CategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public CategoryLanguageDAO getCategoryLanguageDao() {
		return categoryLanguageDao;
	}

	public void setCategoryLanguageDao(CategoryLanguageDAO categoryLanguageDao) {
		this.categoryLanguageDao = categoryLanguageDao;
	}

}




