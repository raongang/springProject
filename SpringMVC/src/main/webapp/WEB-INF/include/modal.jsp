<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	  
	  <!-- 처리결과에 따른 MODAL(일반) -->
	  <div class="modal fade modal-center" id="resultModal" tabindex="-1" role="dialog">
	  	<div class="modal-dialog modal-sm modal-center" role="document">
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header" id="getHeader">
	          <h4 class="modal-title">Result</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body" id="getBody"></div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	    </div>
	  </div><!-- modal fade -->
	  
	  <div class="modal fade modal-center" id="confirmModal" tabindex="-1" role="dialog">
	  	<div class="modal-dialog modal-sm modal-center" role="document">
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-body" id="getBody">Are you Sure?</div>
	        <div class="modal-footer">
	          <button type="button" data-dismiss="modal" class="btn btn-primary modal_delete">Delete</button>
	          <button type="button" data-dismiss="modal" class="btn btn-primary modal_cancel">Cancel</button>
	        </div>
	      </div>
	    </div>
	  </div><!-- modal fade -->	  