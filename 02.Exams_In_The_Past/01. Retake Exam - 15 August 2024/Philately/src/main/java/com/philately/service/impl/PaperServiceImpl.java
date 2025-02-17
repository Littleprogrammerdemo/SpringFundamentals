package com.philately.service.impl;

import com.philately.constant.Constants;
import com.philately.model.entity.Paper;
import com.philately.model.entity.PaperName;
import com.philately.repository.PaperRepository;
import com.philately.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {

    private final PaperRepository paperRepository;

    @Override
    public void initData() {

        if (this.paperRepository.count() == 0) {
            List<Paper> papers = new ArrayList<>();

            for (PaperName value : PaperName.values()) {
                Paper paper = new Paper();
                paper.setName(value);
                paper.setDescription(getPaperInfo(value));
                papers.add(paper);
            }

            paperRepository.saveAll(papers);
        }
    }

    @Override
    public Paper findByName(PaperName paperName) {
        return this.paperRepository.findByName(paperName).orElse(null);
    }

    private String getPaperInfo(PaperName name) {
        return switch (name) {
            case WOVE_PAPER -> Constants.WOVE_PAPER;
            case LAID_PAPER -> Constants.LAID_PAPER;
            case GRANITE_PAPER -> Constants.GRANITE_PAPER;
        };
    }

}
