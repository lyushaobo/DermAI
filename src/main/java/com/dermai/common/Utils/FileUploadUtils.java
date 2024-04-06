package com.dermai.common.Utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dermai.common.constants.Constants;
import com.dermai.common.exception.file.FileNameLengthLimitExceededException;
import com.dermai.common.exception.file.FileSizeLimitExceededException;
import com.dermai.common.exception.file.InvalidExtensionException;
import com.dermai.framework.config.DermAIConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Shaobo
 */
public class FileUploadUtils {

    @Value("${dermai.workerId}")
    private static long workerId;
    private static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * upload file
     *
     * @param baseDir base direction
     * @param file upload file
     * @return file name
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * upload file
     *
     * @param baseDir base direction
     * @param file upload file
     * @param allowedExtension allowed extension
     * @return return file name
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException {
        // get file name length
        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        // is allowed extension
        assertAllowed(file, allowedExtension);
        // get file name
        String fileName = extractFilename(file);
        // get absolute file path
        String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
        // save file
        file.transferTo(Paths.get(absPath));
        return getPathFileName(baseDir, fileName);
    }

    public static final String getPathFileName(String uploadDir, String fileName) throws IOException {
        System.out.println(DermAIConfiguration.getUploadPath());
        int dirLastIndex = DermAIConfiguration.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        return Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
    }

    public static File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static final String extractFilename(MultipartFile file) {
        // 2024/04/06/单寸证件照_20240406170607A001.jpg
        String datePath = DateUtil.format(LocalDateTime.now(), "yyyy/MM/dd");
        String filePrefix = FileUtil.getPrefix(file.getOriginalFilename());
        return StrUtil.format("{}/{}_{}.{}", datePath,
                filePrefix, IdUtil.getSnowflake(workerId).nextIdStr(), getExtension(file));
    }

    public static void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException {
        long size = file.getSize();
        if (size > DermAIConfiguration.getMaxFileSize()) {
            throw new FileSizeLimitExceededException(DermAIConfiguration.getMaxFileSize() / 1024 / 1024);
        }
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.VIDEO_EXTENSION) {
                throw new InvalidExtensionException.InvalidVideoExtensionException(allowedExtension, extension,
                        fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }
    }

    private static boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private static String getExtension(MultipartFile file) {
        String extension = FileNameUtil.extName(file.getOriginalFilename());
        if (StrUtil.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }
}
