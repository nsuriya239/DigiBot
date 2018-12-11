package com.digitail.command;

import java.io.Serializable;

import com.digitail.bean.PageObjectBean;

public interface PayloadCommand {

	public Serializable execute(PageObjectBean bean);

}
