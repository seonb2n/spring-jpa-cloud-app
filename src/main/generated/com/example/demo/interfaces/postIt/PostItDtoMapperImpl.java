package com.example.demo.interfaces.postIt;

import com.example.demo.domain.postIt.PostItCommand.RegisterPostIt;
import com.example.demo.domain.postIt.PostItCommand.RegisterPostIt.RegisterPostItBuilder;
import com.example.demo.domain.postIt.PostItCommand.UpdatePostIt;
import com.example.demo.domain.postIt.PostItCommand.UpdatePostIt.UpdatePostItBuilder;
import com.example.demo.domain.postIt.PostItCommand.UpdatePostItUnit;
import com.example.demo.domain.postIt.PostItCommand.UpdatePostItUnit.UpdatePostItUnitBuilder;
import com.example.demo.interfaces.postIt.PostItDto.RegisterRequest;
import com.example.demo.interfaces.postIt.PostItDto.UpdatePostItRequest;
import com.example.demo.interfaces.postIt.PostItDto.UpdatePostItRequestUnit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:08:13+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class PostItDtoMapperImpl implements PostItDtoMapper {

    @Override
    public RegisterPostIt of(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        RegisterPostItBuilder registerPostIt = RegisterPostIt.builder();

        registerPostIt.userToken( registerRequest.getUserToken() );
        registerPostIt.content( registerRequest.getContent() );
        registerPostIt.status( registerRequest.getStatus() );
        registerPostIt.categoryName( registerRequest.getCategoryName() );

        return registerPostIt.build();
    }

    @Override
    public UpdatePostItUnit of(UpdatePostItRequestUnit updatePostItRequestUnit) {
        if ( updatePostItRequestUnit == null ) {
            return null;
        }

        UpdatePostItUnitBuilder updatePostItUnit = UpdatePostItUnit.builder();

        updatePostItUnit.content( updatePostItRequestUnit.getContent() );
        updatePostItUnit.status( updatePostItRequestUnit.getStatus() );
        updatePostItUnit.categoryName( updatePostItRequestUnit.getCategoryName() );
        updatePostItUnit.categoryToken( updatePostItRequestUnit.getCategoryToken() );
        updatePostItUnit.postItToken( updatePostItRequestUnit.getPostItToken() );

        return updatePostItUnit.build();
    }

    @Override
    public UpdatePostIt of(UpdatePostItRequest updatePostItRequest) {
        if ( updatePostItRequest == null ) {
            return null;
        }

        UpdatePostItBuilder updatePostIt = UpdatePostIt.builder();

        updatePostIt.userToken( updatePostItRequest.getUserToken() );
        updatePostIt.updatePostItUnitList( updatePostItRequestUnitListToUpdatePostItUnitList( updatePostItRequest.getUpdatePostItUnitList() ) );

        return updatePostIt.build();
    }

    protected List<UpdatePostItUnit> updatePostItRequestUnitListToUpdatePostItUnitList(List<UpdatePostItRequestUnit> list) {
        if ( list == null ) {
            return null;
        }

        List<UpdatePostItUnit> list1 = new ArrayList<UpdatePostItUnit>( list.size() );
        for ( UpdatePostItRequestUnit updatePostItRequestUnit : list ) {
            list1.add( of( updatePostItRequestUnit ) );
        }

        return list1;
    }
}
