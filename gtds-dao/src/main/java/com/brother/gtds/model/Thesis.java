package com.brother.gtds.model;

import java.io.Serializable;
import java.util.Date;

//论文
public class Thesis implements Serializable {

	private static final long serialVersionUID = 902349944245940853L;

	private Integer id;
	private String title;
	// 论文说明
	private String explain;
	private Date date;
	// 论文路径
	private String path;
	// 指导老师评分
	private Integer directorScore;
	// 评阅老师评分
	private Integer inspectorScore;
	// 答辩小组评分
	private Integer answerScore;
	// 汇总分数
	/*
	 * 学生的毕业论文（设计）总评成绩，采用五级计分制计分（优、良、中、及格、不及格），
	 * 以“结构分”进行综合评定。结构分由指导教师的评分、评阅人的评分、答辩小组的评分三
	 * 部分组成。指导教师的评分、评阅人的评分各占30%，答辩小组的评分占40%。90分以上
	 * 为优，80-89分为良，70-79分为中，60-69为及格，60分以下为不及格。
	 */
	private Integer summaryScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getDirectorScore() {
		return directorScore;
	}

	public void setDirectorScore(Integer directorScore) {
		this.directorScore = directorScore;
	}

	public Integer getInspectorScore() {
		return inspectorScore;
	}

	public void setInspectorScore(Integer inspectorScore) {
		this.inspectorScore = inspectorScore;
	}

	public Integer getAnswerScore() {
		return answerScore;
	}

	public void setAnswerScore(Integer answerScore) {
		this.answerScore = answerScore;
	}

	public Integer getSummaryScore() {
		return summaryScore;
	}

	public void setSummaryScore(Integer summaryScore) {
		this.summaryScore = summaryScore;
	}

}
