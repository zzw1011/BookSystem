package com.zzw.book.dao.impl;

import com.zzw.base.dao.impl.BaseDaoImpl;
import com.zzw.book.dao.BookDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建者：zzw
 * @创建时间 2018/1/11 0011
 * @描述
 */
@Repository("bookDaoImpl")
public class BookDaoImpl<BookEntity> extends BaseDaoImpl<BookEntity> implements BookDao<BookEntity>{
    @Override
    public int insert(BookEntity bookEntity) {
        return super.save("insertBook",bookEntity);
    }

    @Override
    public int update(BookEntity bookEntity) {
        return super.update("updateBook",bookEntity);
    }

    @Override
    public int delete(Long id) {
        return super.delete("deleteBook", id);
    }

    @Override
    public int delete(List<Long> ids) {
        Map<String, Object> model = new HashMap<>();
        model.put("list",ids);
        return super.delete("deleteBookIds",model);
    }
}
