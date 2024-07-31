package com.ssafy.foss.feedback.service;

import com.ssafy.foss.feedback.domain.MenteeFeedback;
import com.ssafy.foss.feedback.domain.MenteeFeedbackId;
import com.ssafy.foss.feedback.dto.request.MenteeFeedbackRequest;
import com.ssafy.foss.feedback.dto.response.FeedbackMenteeInfoResponse;
import com.ssafy.foss.feedback.dto.response.MenteeFeedbackPendingResponse;
import com.ssafy.foss.feedback.repository.MenteeFeedbackRepository;
import com.ssafy.foss.interview.repository.InterviewRepository;
import com.ssafy.foss.respondent.repository.RespondentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FeedbackServiceImpl implements FeedbackService {
    private final InterviewRepository interviewRepository;
    private final RespondentRepository respondentRepository;
    private final MenteeFeedbackRepository menteeFeedbackRepository;

    @Override
    public List<MenteeFeedbackPendingResponse> findPendingMenteeFeedback(Long memberId) {
        List<MenteeFeedbackPendingResponse> pendingResponses = interviewRepository.findPendingFeedbackInterviews(memberId);

        for (MenteeFeedbackPendingResponse response : pendingResponses) {
            List<FeedbackMenteeInfoResponse> menteeInfos = respondentRepository.findOtherRespondents(response.getInterviewId(), memberId);
            for (FeedbackMenteeInfoResponse menteeInfo : menteeInfos) {
                response.addMenteeInfo(menteeInfo);
            }
        }

        return pendingResponses;
    }

    @Override
    @Transactional
    public void createMenteeFeedback(List<MenteeFeedbackRequest> menteeFeedbackRequests, Long memberId) {
        for (MenteeFeedbackRequest menteeFeedbackRequest : menteeFeedbackRequests) {
            menteeFeedbackRepository.save(buildAndSaveMenteeFeedback(menteeFeedbackRequest, memberId));
        }
    }

    public MenteeFeedback buildAndSaveMenteeFeedback(MenteeFeedbackRequest menteeFeedbackRequest, Long memberId) {
        MenteeFeedbackId menteeFeedbackId = new MenteeFeedbackId(menteeFeedbackRequest.getRespondentId(), memberId);

        return MenteeFeedback.builder()
                .id(menteeFeedbackId)
                .content(menteeFeedbackRequest.getContent())
                .build();
    }
}
