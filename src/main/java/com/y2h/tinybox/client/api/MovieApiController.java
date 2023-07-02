package com.y2h.tinybox.client.api;

import com.y2h.tinybox.client.movie.service.MovieService;
import com.y2h.tinybox.client.movie.service.dto.MovieDetailDto;
import com.y2h.tinybox.client.movie.service.dto.MovieDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"영화"})
@RequestMapping("/movie")
public class MovieApiController {

    private final MovieService movieService;

    @ApiOperation(value = "영화 리스트 출력")
    @GetMapping("")
    public List<MovieDto> listMovies(@RequestParam("key") String key, @RequestParam("word") String word, @RequestParam("period") String period) {
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("word", word);
        map.put("period", period);
        return movieService.getMovie(map);
    }
}
