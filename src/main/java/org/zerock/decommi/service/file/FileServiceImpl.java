// package org.zerock.decommi.service.file;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;
// import org.zerock.decommi.dto.FileDTO;
// import org.zerock.decommi.entity.diary.File;
// import org.zerock.decommi.repository.diary.FileRepository;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Service
// @RequiredArgsConstructor
// @Log4j2
// public class FileServiceImpl implements FileService{
//   private final FileRepository repository;

//   @Override
//   public Long registerFile(FileDTO dto) {
//     log.info("=====Registering File=====");
//     File file = dtoToEntity(dto);
//     repository.save(file);
//     return dto.getDino(); // 해당 게시글에 파일 업로드 
//   }
//   @Override
//   public void removeFile(Long fid) {
//     log.info("=====Remove File=====");
//     repository.deleteById(fid);
//   }
//   @Override
//   public List<FileDTO> getFileWithDino(Long dino) {
//     List<File> result = repository.findByDino(dino);
//     return result.stream().map(e->entityToDTO(e)).collect(Collectors.toList());
//   }

//   @Override
//   public FileDTO getFileWithFid(Long fid) {
//     Optional<File> result = repository.findById(fid);
//     return entityToDTO(result.get());
//   }

//   @Override
//   public Long registerMultipleFile(List<FileDTO> result) {
//     for (int i = 0; i < result.size(); i++) {
//       FileDTO dto = result.get(i);
//       File file = dtoToEntity(dto);
//       repository.save(file);
//     }
//     return result.get(0).getDino();
//   }
// }
