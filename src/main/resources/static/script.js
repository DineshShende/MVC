
$(function(){

/* attach a submit handler to the form */
	$("#sendSMS").submit(function(event) {

	    /* stop form from submitting normally */
	    event.preventDefault();

	    /* get some values from elements on the page: */
	    var $form = $(this),
	    customerId_par = $form.find('input[name="customerId"]').val(),
	    customerType_par = $form.find('input[name="customerType"]').val(),
	    mobileType_par = $form.find('input[name="mobileType"]').val(),
	        url = $form.attr('action');

	    /* Send the data using post */
	    var posting = $.post(url, {
	    	customerId: customerId_par,
	    	customerType: customerType_par,
	    	mobileType : mobileType_par
	        
	    });

	    /* Put the results in a div */
	    posting.done(function(data) {
	        	var content = $(data).find('#content');

	        $("#smsSentStatus").empty().append("Status:"+data);
	    });
	});

	$("#verifyMobile").submit(function(event) {

	    /* stop form from submitting normally */
	    event.preventDefault();

	    /* get some values from elements on the page: */
	    var $form = $(this),
	    customerId_par = $form.find('input[name="entityId"]').val(),
	    customerType_par = $form.find('input[name="entityType"]').val(),
	    mobileType_par = $form.find('input[name="mobileType"]').val(),
	    mobilePin_par = $form.find('input[name="mobilePin"]').val(),
	        url = $form.attr('action');

	    /* Send the data using post */
	    var posting = $.post(url, {
	    	entityId: customerId_par,
	    	entityType: customerType_par,
	    	mobileType : mobileType_par,
	    	mobilePin : mobilePin_par
	        
	    });

	    /* Put the results in a div */
	    posting.done(function(data) {
	        var content = $(data).find('#content');
	        
	        $("#verifyMobileStatus").empty().append("Status:"+data);
	    });
	});


	$("#verifyMobileSec").submit(function(event) {

	    /* stop form from submitting normally */
	    event.preventDefault();

	    /* get some values from elements on the page: */
	    var $form = $(this),
	    customerId_par = $form.find('input[name="entityId"]').val(),
	    customerType_par = $form.find('input[name="entityType"]').val(),
	    mobileType_par = $form.find('input[name="mobileType"]').val(),
	    mobilePin_par = $form.find('input[name="mobilePin"]').val(),
	        url = $form.attr('action');

	    /* Send the data using post */
	    var posting = $.post(url, {
	    	entityId: customerId_par,
	    	entityType: customerType_par,
	    	mobileType : mobileType_par,
	    	mobilePin : mobilePin_par
	        
	    });

	    /* Put the results in a div */
	    posting.done(function(data) {
	        var content = $(data).find('#content');
	        
	        $("#verifyMobileStatusSec").empty().append("Status:"+data);
	    });
	});


	$("#sendSMSSec").submit(function(event) {

	    /* stop form from submitting normally */
	    event.preventDefault();

	    /* get some values from elements on the page: */
	    var $form = $(this),
	    customerId_par = $form.find('input[name="customerId"]').val(),
	    customerType_par = $form.find('input[name="customerType"]').val(),
	    mobileType_par = $form.find('input[name="mobileType"]').val(),
	        url = $form.attr('action');

	    /* Send the data using post */
	    var posting = $.post(url, {
	    	customerId: customerId_par,
	    	customerType: customerType_par,
	    	mobileType : mobileType_par
	        
	    });

	    /* Put the results in a div */
	    posting.done(function(data) {
	        var content = $(data).find('#content');
	        
	        $("#smsSentStatusSec").empty().append("Status:"+data);
	    });
	});


	$("#sendEmail").submit(function(event) {

	    /* stop form from submitting normally */
	    event.preventDefault();

	    /* get some values from elements on the page: */
	    var $form = $(this),
	    customerId_par = $form.find('input[name="customerId"]').val(),
	    customerType_par = $form.find('input[name="customerType"]').val(),
	    emailType_par = $form.find('input[name="emailType"]').val(),
	        url = $form.attr('action');

	    /* Send the data using post */
	    var posting = $.post(url, {
	    	customerId: customerId_par,
	    	customerType: customerType_par,
	    	emailType : emailType_par
	        
	    });

	    /* Put the results in a div */
	    posting.done(function(data) {
	        var content = $(data).find('#content');
	        
	        $("#emailSentStatus").empty().append("Status:"+data);
	    });
	});         
            
});    