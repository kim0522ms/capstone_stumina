window.onload = function() {
	var isNotRead = $('#isNotRead').attr('value');
	console.log(isNotRead);
	if (isNotRead == 'true')
	{
		$('#circle').attr('style','');
	}
}

new Vue({
  el: '.notification_container',
  data: function data() {
    return {
      users: [],
      errors: [],
      show: true
    };
  },
/*
  mounted: function mounted() {
    this.getUsers();
  },


  methods: {
    getUsers: function getUsers() {
      var _this = this;

      axios.get('https://randomuser.me/api/?results=3').then(function (response) {
        console.log(JSON.stringify(response.data.results));
        _this.users = response.data.results;
      }).catch(function (e) {
        _this.errors.push(e);
      });
    }
  },

  filters: {
    capitalize: function capitalize(value) {
      if (!value) return '';
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    }
  }*/
});

function readNotification(noti_idx){
	$.ajax({
		type : "GET",
        url : location.host + "/Graduation_KMS/op/readNofitication",
        data : {
        	noti_idx : noti_idx
		},
        dataType : "text",
        success : function(data){
        	document.getElementById(noti_idx).style.background = 'white';
        },
		error : function(){
            alert('통신실패!!');
        },
    });
}