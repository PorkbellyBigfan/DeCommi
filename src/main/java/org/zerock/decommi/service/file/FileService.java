// package org.zerock.decommi.service.file;

// import java.util.List;

// import org.zerock.decommi.dto.FileDTO;
// import org.zerock.decommi.entity.diary.Diary;
// import org.zerock.decommi.entity.diary.File;

// public interface FileService {
//   void removeFile(Long fid);
//   void removeUuid(String uuid);
//   Long registerFile(FileDTO dto);
//   FileDTO getFileWithFid(Long fid);
//   List<FileDTO>getFileWithDino(Long dino);


//   Long registerMultipleFile(List<FileDTO> result);

//   default File dtoToEntity(FileDTO dto){
//     File file = File.builder()
//       .uuid(dto.getUuid())
//       .fname(dto.getImgName())
//       .fpath(dto.getFpath())
//       .diary(Diary.builder().dino(dto.getDino()).build())
//       .build();
//     return file;
//   }
//   default FileDTO entityToDTO(File file){
//     FileDTO dto = FileDTO.builder()
//       .dino(file.getDiary().getDino())
//       .imgName(file.getFname())
//       .fpath(file.getFpath())
//       .uuid(file.getUuid())
//       .build();
//     return dto;
//   }
// }
