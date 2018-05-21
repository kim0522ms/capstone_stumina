package com.example.study.controller;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileRenamePolicy extends DefaultFileRenamePolicy {	  
  public File rename(File f) {		//File f�� ���� ����
    if (createNewFile(f)) return f;	//������ f��
    
    //Ȯ���ڰ� ���� ���� �϶� ó��
    String name = f.getName();
    String body = null;
    String ext = null;

    int dot = name.lastIndexOf(".");
    if (dot != -1) {	//Ȯ���ڰ� ������
      body = name.substring(0, dot);
      ext = name.substring(dot);
    } else {			//Ȯ���ڰ� ������
      body = name;
      ext = "";
    }

    int count = 0;
    //�ߺ��� ������ ������
    while (!createNewFile(f) && count < 9999) {		
      count++;
      String newName = body + "("+ count + ")" + ext;
      f = new File(f.getParent(), newName);
    }
    return f;
  }

  private boolean createNewFile(File f) {	
    try {
      return f.createNewFile();		//�����ϴ� ������ �ƴϸ�
    }catch (IOException ignored) {
      return false;
    }
  }
}
