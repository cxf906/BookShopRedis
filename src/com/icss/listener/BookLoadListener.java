package com.icss.listener;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import com.icss.bk.biz.BookBiz;
import com.icss.bk.dto.BookJson;
import com.icss.bk.entity.TBook;
import com.icss.bk.util.RedisUtil;


public class BookLoadListener implements  ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}	
	
	
	public void contextInitialized(ServletContextEvent arg0) {
		Jedis jedis = RedisUtil.getJedis();
		loadAllBooks(jedis);
		setTimeSortBooks(jedis);
		RedisUtil.returnResource(jedis);
	}
	
	/**
	 * 系统启动时，把所有的图书信息都装载到redis数据库中
	 */
	private void loadAllBooks(Jedis jedis){
		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getAllBooks();	                         //从数据库中读取所有图书   
			Map<String, String> map = new HashMap<String, String>();
			for(TBook bk : books){				
				BookJson bkj = new BookJson();
				bkj.setBname(bk.getBname());
				bkj.setIsbn(bk.getIsbn());
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");				
				bkj.setPdate(sd.format(bk.getPdate()));
				bkj.setPicurl(bk.getPicurl());
				bkj.setPress(bk.getPress());
				bkj.setPrice(bk.getPrice());
				bkj.setPageview(0);
				JSONArray jsonArray = JSONArray.fromObject(bkj); 			
				String bkString = jsonArray.toString();                        //每本书生成一个json串		
				map.put(bk.getIsbn(), bkString);
			}			
			jedis.hmset("allBookList",map);                                    //把所有图书，存于hash map中
		} catch (Exception e) {
			 e.printStackTrace();
		}		
	}
	/**
	 * 主页图书，按照上架时间进行排序显示
	 */
	private void setTimeSortBooks(Jedis jedis){
		BookBiz biz = new BookBiz();
		try {
			List<TBook> books = biz.getAllBooks();	                         //从数据库中读取所有图书 
			for(TBook bk : books){		
				long time = bk.getPdate().getTime();
				jedis.zadd("bkTimeZset", time,bk.getIsbn());
			}			
		} catch (Exception e) {
			 e.printStackTrace();
		}				
	}
}
