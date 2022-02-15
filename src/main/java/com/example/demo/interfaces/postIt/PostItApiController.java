package com.example.demo.interfaces.postIt;

import com.example.demo.application.postIt.PostItFacade;
import com.example.demo.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/postIts")
public class PostItApiController {

    private final PostItFacade postItFacade;
    private final PostItDtoMapper postItDtoMapper;

    @PostMapping("/register")
    public CommonResponse registerPostIt(@RequestBody PostItDto.RegisterRequest registerRequest) {
        // 1. registerRequest(dto mapper 로 변환) -> command
        // 2. postItFacade 에서 command 로 등록
        // 3. 결과로 나온 postItInfo -> registerResponse
        // 4. CommonResponse 로 감싸서 return

        var postItCommand = postItDtoMapper.of(registerRequest);
        var postItInfo = postItFacade.registerPostIt(postItCommand);
        var postItResponse = new PostItDto.RegisterResponse(postItInfo);
        return CommonResponse.success(postItResponse);
    }

}
