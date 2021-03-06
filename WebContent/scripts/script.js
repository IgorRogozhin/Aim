//Calendar
$(function() {
	$("#start-date").datepicker({
		minDate : 0,
		dateFormat : "yy-mm-dd",
		changeYear : true,
		dayNamesMin : [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ],
	});
});

// Style href
$(function() {
	$(".link").button().buttonset();
});

// Style tables
$(function() {
	$("#deadlineTable").addClass("ui-widget");
	$("#deadlineTable").addClass("styleTable");
	$("#headerTest").addClass("ui-widget-header");
	$("#headerTest").addClass("center");
	$("#nameOfTable").addClass("styleNameOfTable");
	$(".button").button();
	$("#addAimTable").addClass("ui-widget");
	$("#addAimTable").addClass("center");
	$("#groupDeadlineTable").addClass("ui-widget");
	$("#groupDeadlineTable").addClass("center");
	$("#groupDeadlineTable").addClass("styleTable");
	$("#createGroupTable").addClass("ui-widget");
	$("#createGroupTable").addClass("center");
	$("#joinGroupTable").addClass("ui-widget");
	$("#joinGroupTable").addClass("center");
	$("#signoutGroupTable").addClass("ui-widget");
	$("#signoutGroupTable").addClass("center");
	$("#deleteGroupTable").addClass("ui-widget");
	$("#deleteGroupTable").addClass("center");
	$("#groupArchiveTable").addClass("ui-widget");
	$("#groupArchiveTable").addClass("center");
	$("#groupArchiveTable").addClass("styleTable");
	$("#groupRateTable").addClass("ui-widget");
	$("#groupRateTable").addClass("center");
	$("#groupRateTable").addClass("styleTable");
	$("#archiveTable").addClass("ui-widget");
	$("#archiveTable").addClass("center");
	$("#archiveTable").addClass("styleTable");
});

// Style other
$(function() {
	$("#userName").addClass("deepBlue");
	$("#moto").addClass("commonColor");
	// $("#loginButton").addClass("button");
	$("#login").addClass("cabinet");
	$("#registration").addClass("cabinet");
	$("#buttonIn").button();
	$("#registrationButton").button();
	$("#wrongRepeatedPwd").addClass("attention");
	$("#emptyFields").addClass("attention");
	$("#signoutButton").button();
	$("#tempoMessage").hide();

});

$(function() {
	$("#static-tabbed-panel-div").tabs();
	$("#static-tabbed-panel-div").addClass("center");
});

// Registration checking
$(function() {
	$("#wrongRepeatedPwd").hide();
	$("#emptyFields").hide();
});
var RegUtils = {};
RegUtils.checkLoginCorrectness = function() {
	if ($("#loginString").val().length == 0 || $("#loginPwd").val().length == 0) {
		$("#emptyFields").show();
		return false;
	}
	return true;
}
RegUtils.checkCorrectness = function() {
	if ($("#pwd").val().length == 0 || $("#pwd2").val().length == 0
			|| $("#loginField2").val().length == 0) {
		$("#emptyFields").show();
		return false;
	}
	if (!($("#pwd").val() === $("#pwd2").val())) {
		$("#wrongRepeatedPwd").show();
		return false;
	}
	return true;
}

// Failed, delete, achieved buttons
$(function() {
	$("#deadlineTable tr").mouseover(function() {
		$(this).addClass("active");
	});
	$("#deadlineTable tr").mouseout(function() {
		$(this).removeClass("active");
	});

	$(".passive .toServer #failedButt").click(function() {
		// Failed aim
		var userId = $(".active .toServer #id").val();
		var nameOfAim = $(".active .toServer #nameOfAim").val();
		var description = $(".active .toServer #description").val();
		$.ajax({
			url : '../Protected/failedAim.do',
			data : {
				userId : userId,
				nameOfAim : nameOfAim,
				description : description
			},
			success : function(responseText) {
				RegUtils.actions(responseText, nameOfAim);
			}
		});
	});
	// Delete aim
	$(".passive .toServer #deleteButt").click(function() {
		var userId = $(".active .toServer #id").val();
		var nameOfAim = $(".active .toServer #nameOfAim").val();
		var description = $(".active .toServer #description").val();
		$.ajax({
			url : '../Protected/deleteAim.do',
			data : {
				userId : userId,
				nameOfAim : nameOfAim,
				description : description
			},
			success : function(responseText) {
				RegUtils.actions(responseText, nameOfAim);
			}
		});
	});
	// Achieved aim
	$(".passive .toServer #doneButt").click(function() {
		var userId = $(".active .toServer #id").val();
		var nameOfAim = $(".active .toServer #nameOfAim").val();
		var description = $(".active .toServer #description").val();
		$.ajax({
			url : '../Protected/doneAim.do',
			data : {
				userId : userId,
				nameOfAim : nameOfAim,
				description : description
			},
			success : function(responseText) {
				RegUtils.actions(responseText, nameOfAim);
			}
		});
	});
});

RegUtils.actions = function(fromServer, nameOfAim) {
	if (fromServer == "failed") {
		$(".active").hide();
		$("#tempoMessage").text(
				"Не удалось справиться с задачей '" + nameOfAim + "'? "
						+ "Bремя сосредоточиться на следующей!").show(
				"highlight", {
					color : "#758bc6"
				}, 1000);
		$("#tempoMessage").addClass("attention");
	} else if (fromServer == "deleted") {
		$(".active").hide();
		$("#tempoMessage").text("Задача '" + nameOfAim + "' удалена").show(
				"highlight", {
					color : "#758bc6"
				}, 1000);
		$("#tempoMessage").addClass("success");
	} else if (fromServer == "done") {
		$(".active").hide();
		$("#tempoMessage").text(
				"Задача '" + nameOfAim + "' успешно выполнена, вы молодец!")
				.show("highlight", {
					color : "#758bc6"
				}, 1000);
		$("#tempoMessage").addClass("success");
	}

};