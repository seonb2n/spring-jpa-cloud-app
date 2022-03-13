package com.example.demo.interfaces.postIt;

import com.example.demo.application.postIt.PostItFacade;
import com.example.demo.common.response.CommonResponse;
import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.postIt.PostItInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/postits")
public class PostItApiController {

    private final PostItFacade postItFacade;
    private final PostItDtoMapper postItDtoMapper;

    @PostMapping("/update")
    public CommonResponse updatePostIt(@RequestBody PostItDto.UpdatePostItRequest updatePostItRequest) {
        var postItUpdateCommand = postItDtoMapper.of(updatePostItRequest);
        var postItUpdateResponse = postItFacade.updateAllPostIt(postItUpdateCommand);
        return CommonResponse.success(postItUpdateResponse);
    }


}
