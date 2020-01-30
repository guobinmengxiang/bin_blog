package bin_blog;

import org.junit.Test;

import com.bin.blog.service.BlogService;

public class test {
private BlogService bs;
	@Test
    public void findAll() {
		bs.getBlogsForIndexPage(1);
    }

}
