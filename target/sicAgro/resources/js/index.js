var app = angular.module('dmApp',[]);

app.controller('MenuController', function($scope) {
  $scope.line = true;
  $scope.context = false;
  
  $scope.init = function() {
    window.onresize = $scope.resize;
  }
  
  $scope.close = function($event) {
    e = $event
    if(e.target.className !== "ce" ||  e.target.className === "" ) {
        if(e.target.className === "fa fa-caret-down ce" ) {  return; }
        if($scope.context) {
          $scope.context = false;
        }
      } 
  }
  
  $scope.closeMenu = function() {
    var w = window.innerWidth

    if(w < 768) {
      if(document.querySelector('#page').className === 'menu_small' || $scope.line ) {
        document.querySelector('#page').className = 'menu_hide'
        document.querySelector('#menu_container').className = 'menu_hide'
        document.querySelector('#account_menu').className = 'menu_hide'
        $scope.context = false

        $scope.line = false
      } else {
        document.querySelector('#page').className = 'menu_small'
        document.querySelector('#menu_container').className = 'menu_small'
        document.querySelector('#account_menu').className = 'menu_small'
        $scope.line = true
        $scope.context = false
      }
    } else {
      if(document.querySelector('#page').className === 'menu_small') {
        document.querySelector('#page').className = ''
        document.querySelector('#menu_container').className = ''
        document.querySelector('#account_menu').className = ''
        
        $scope.line = true
      } else {
        document.querySelector('#page').className = 'menu_small'
        document.querySelector('#menu_container').className = 'menu_small'
        document.querySelector('#account_menu').className = 'menu_small'
        $scope.line = true
        $scope.context = false
      }
    }
  
  }
  
  $scope.resize = function() {
    line = $scope.line
    
    var w = window.innerWidth
    if(w<768) {
      console.log('<768', line)
      if(line) {
        document.querySelector('#page').className = 'menu_small'
        document.querySelector('#menu_container').className = 'menu_small'
      } else {
        document.querySelector('#page').className = 'menu_small'
        document.querySelector('#menu_container').className = 'menu_small'
      }
      document.querySelector('#account_menu').className = 'menu_hide'

    } else {
      if(!line ) {
        document.querySelector('#page').className = '';
        document.querySelector('#menu_container').className = '';
        document.querySelector('#account_menu').className = ''
      } else {

      }

    }
  }
  
  $scope.openContext = function() {
    if($scope.context) {
      $scope.context = false;
    } else {
      $scope.context = true;
    }
  }
  
  $scope.init();
   
})