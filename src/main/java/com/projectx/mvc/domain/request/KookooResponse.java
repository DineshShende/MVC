package com.projectx.mvc.domain.request;

public class KookooResponse {

	private String sid;
	
	private String status;
	
	private String phone_no;
	
	private String start_time;
	
	private String ringing_time;
	
	private String duration;
	
	private String caller_id;
	
	private String status_details;

	public KookooResponse() {
		super();
	}

	public KookooResponse(String sid, String status, String phone_no,
			String start_time, String ringing_time, String duration,
			String caller_id, String status_details) {
		super();
		this.sid = sid;
		this.status = status;
		this.phone_no = phone_no;
		this.start_time = start_time;
		this.ringing_time = ringing_time;
		this.duration = duration;
		this.caller_id = caller_id;
		this.status_details = status_details;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getRinging_time() {
		return ringing_time;
	}

	public void setRinging_time(String ringing_time) {
		this.ringing_time = ringing_time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCaller_id() {
		return caller_id;
	}

	public void setCaller_id(String caller_id) {
		this.caller_id = caller_id;
	}

	public String getStatus_details() {
		return status_details;
	}

	public void setStatus_details(String status_details) {
		this.status_details = status_details;
	}

	@Override
	public String toString() {
		return "KookooResponse [sid=" + sid + ", status=" + status
				+ ", phone_no=" + phone_no + ", start_time=" + start_time
				+ ", ringing_time=" + ringing_time + ", duration=" + duration
				+ ", caller_id=" + caller_id + ", status_details="
				+ status_details + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caller_id == null) ? 0 : caller_id.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result
				+ ((phone_no == null) ? 0 : phone_no.hashCode());
		result = prime * result
				+ ((ringing_time == null) ? 0 : ringing_time.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result
				+ ((start_time == null) ? 0 : start_time.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((status_details == null) ? 0 : status_details.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KookooResponse other = (KookooResponse) obj;
		if (caller_id == null) {
			if (other.caller_id != null)
				return false;
		} else if (!caller_id.equals(other.caller_id))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (phone_no == null) {
			if (other.phone_no != null)
				return false;
		} else if (!phone_no.equals(other.phone_no))
			return false;
		if (ringing_time == null) {
			if (other.ringing_time != null)
				return false;
		} else if (!ringing_time.equals(other.ringing_time))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (status_details == null) {
			if (other.status_details != null)
				return false;
		} else if (!status_details.equals(other.status_details))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
