package com.fulan.api.plan.vo;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class StudyPlanManageVo  implements Serializable {
private List<StudyPlanCourse> studyPlanCourseList;


}
