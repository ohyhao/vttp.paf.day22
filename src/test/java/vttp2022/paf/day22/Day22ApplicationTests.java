package vttp2022.paf.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.day22.services.GiphyService;

@SpringBootTest
class Day22ApplicationTests {

	@Autowired
	private GiphyService giphySvc;

	@Test
	void shouldLoad10Images() {
		List<String> result = giphySvc.getGiphs("dog");
		assertEquals(10, result.size(), "Default number of gifs");
	}

}
