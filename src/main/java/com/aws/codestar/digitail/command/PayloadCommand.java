package com.aws.codestar.digitail.command;

import java.io.Serializable;

import com.aws.codestar.digitail.bean.PageObjectBean;

public interface PayloadCommand {

	public Serializable execute(PageObjectBean bean);

}
