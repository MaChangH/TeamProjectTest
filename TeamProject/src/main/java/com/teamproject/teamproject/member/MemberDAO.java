package com.teamproject.teamproject.member;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class MemberDAO {

	@Autowired
	private SqlSession ss;
	
	//	ID 비교하는 method
	public Members memberIdCheck(Member m) {
		return new Members(ss.getMapper(MemberMapper.class).getById(m));
	}

	//	로그인 상태일때 true, 아닐 때 false 리턴하는 method
	public boolean loginCheck(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			req.setAttribute("lp", "member/loginSuccess.jsp");
			return true;
		} else {
			req.setAttribute("lp", "member/login.jsp");
			return false;
		}
	}
	
	// 중복 ID 체크하는 method
	public Members idCheck(Member m) {
		return new Members(ss.getMapper(MemberMapper.class).getById(m));
	}
	
	// 중복 닉네임 체크하는 method
	public Members nicknameCheck(Member m) {
		return new Members(ss.getMapper(MemberMapper.class).getByNickname(m));
	}
	
	//	회원가입 하는 method
	public void join(Member m, HttpServletRequest req) {
		try {
			String path = req.getSession().getServletContext().getRealPath("resources/img");

			MultipartRequest mr = new MultipartRequest(req, path, 10485760,	"utf-8", new DefaultFileRenamePolicy());
			m.setTp_m_id(mr.getParameter("tp_m_id")); // id
			m.setTp_m_pw(mr.getParameter("tp_m_pw")); // 비밀번호
			m.setTp_m_name(mr.getParameter("tp_m_name")); // 이름
			m.setTp_m_nick(mr.getParameter("tp_m_nick")); // 별명
			
			String addr1 = mr.getParameter("tp_m_addr1");
			String addr2 = mr.getParameter("tp_m_addr2");
			String addr3 = mr.getParameter("tp_m_addr3");
			String addr = addr1 + "!" + addr2 + "!" + addr3;
			m.setTp_m_addr(addr);

			String c_photo = mr.getFilesystemName("tp_m_photo"); // ㅋ ㅋ.png
			String c_photo_kor = URLEncoder.encode(c_photo, "utf-8").replace("+", " "); // %3A 3A.png
			m.setTp_m_photo(c_photo_kor);

			if (ss.getMapper(MemberMapper.class).join(m) == 1) {
				req.setAttribute("r", "가입성공");
			} else {
				req.setAttribute("r", "DB서버의 문제로 가입에 실패했습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "가입에 실패했습니다");
		}
	}
	
	// login하는 method
	public void login(Member inputM, HttpServletRequest req) {
		try {
			List<Member> dbms = ss.getMapper(MemberMapper.class).getById(inputM);
			if (dbms.size() != 0) {
				Member dbM = dbms.get(0);
				if (dbM.getTp_m_pw().equals(inputM.getTp_m_pw())) {
					req.getSession().setAttribute("loginMember", dbM);
//					req.getSession().setMaxInactiveInterval(10 * 60);
					req.setAttribute("r", "로그인했습니다.");
				} else {
					req.setAttribute("r", "비밀번호를 확인하세요");
				}
			} else {
				req.setAttribute("r", "ID를 확인하세요");
			}
		} catch (Exception e) {
			req.setAttribute("r", "DB에 문제가 있습니다. 다음에 로그인하세요");
		}
	}
	
	// 로그아웃 하는 method
	public void logout(HttpServletRequest req) {
		req.getSession().setAttribute("loginMember", null);
		req.setAttribute("r", "로그아웃 되었습니다");
	}
	
	// 정보 수정할 때 주소 불러오는 method
	public void infoAddr(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		String addr = m.getTp_m_addr();
		String[] infoAddr = addr.split("!");
		req.setAttribute("addr", infoAddr);
	}
	
	// 회원 탈퇴하는 method
	public void bye(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");

			if (ss.getMapper(MemberMapper.class).bye(m) == 1) {
				req.setAttribute("r", "탈퇴 되었습니다");
				req.getSession().setAttribute("loginMember", null);

				String dm_photo = m.getTp_m_photo();
				dm_photo = URLDecoder.decode(dm_photo, "UTF-8");

				String path = req.getSession().getServletContext().getRealPath("resources/img");

				new File(path + "/" + dm_photo).delete();

			} else {
				req.setAttribute("r", "탈퇴에 실패했습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "탈퇴에 실패했습니다");
		}
	}
	
	// 회원정보 보기 전 ID, PW 체크하는 method
	public boolean infoCheck(HttpServletRequest req) {
		
		String id = req.getParameter("tp_m_id");
		String pw = req.getParameter("tp_m_pw");
		Member m = (Member) req.getSession().getAttribute("loginMember");
	
		if (pw.equals(m.getTp_m_pw()) && id.equals(m.getTp_m_id())) {
			return true;
		} else {
			req.setAttribute("r", "정보가 올바르지 않습니다");
			return false;
		}
		
	}
	
	// 회원정보 수정하는 method
	public void update(Member m, HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		com.oreilly.servlet.MultipartRequest mr = null;
		try {
			mr = new com.oreilly.servlet.MultipartRequest(req, path, 10 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());
		} catch (Exception e) {
			req.setAttribute("r", "파일 용량이 너무 큽니다");
			return;
		}

		// 회원정보 조회
		Member lm = (Member) req.getSession().getAttribute("loginMember");

		// 기존 사진 파일명
		String oldFile = lm.getTp_m_photo();

		// 새 파일
		String newFile = mr.getFilesystemName("tp_m_photo");
		try {
			String dm_id = mr.getParameter("tp_m_id");
			String dm_pw = mr.getParameter("tp_m_pw");
			String dm_name = mr.getParameter("tp_m_name");
			String dm_nick = mr.getParameter("tp_m_nick"); // 별명
			
			String addr1 = mr.getParameter("tp_m_addr1");
			String addr2 = mr.getParameter("tp_m_addr2");
			String addr3 = mr.getParameter("tp_m_addr3");
			String addr = addr1 + "!" + addr2 + "!" + addr3;
			m.setTp_m_addr(addr);

			if (newFile == null) {
				newFile = oldFile;
			} else { // 수정했을때
				newFile = URLEncoder.encode(newFile, "utf-8");
				newFile = newFile.replace("+", " ");
			}
			m.setTp_m_id(dm_id);
			m.setTp_m_pw(dm_pw);
			m.setTp_m_name(dm_name);
			m.setTp_m_nick(dm_nick);
			m.setTp_m_photo(newFile);

			// DB 수정하기
			if (ss.getMapper(MemberMapper.class).update(m) == 1) {
				req.setAttribute("r", "수정했습니다");

				// 사이트에 반영하기
				req.getSession().setAttribute("loginMember", m);
				loginCheck(req);

				// 이전 프사 지우기
				if (!oldFile.equals(newFile)) {
					oldFile = URLDecoder.decode(oldFile, "utf-8");
					new File(path + "/" + oldFile).delete();
				}
			} else {
				req.setAttribute("r", "수정에 실패했습니다");

				if (!oldFile.equals(newFile)) {
					newFile = URLDecoder.decode(newFile, "utf-8");
					new File(path + "/" + newFile).delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "수정에 실패했습니다");

			// 새 프로필 지우기
			if (!oldFile.equals(newFile)) {
				try {
					newFile = URLDecoder.decode(newFile, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				new File(path + "/" + newFile).delete();
			}
		}
	}
}
