package com.kmuniz.lostserver.util;

import com.kmuniz.lostserver.data.LostItemEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileParser {
    List<LostItemEntity> parse(MultipartFile file) throws IOException;
}