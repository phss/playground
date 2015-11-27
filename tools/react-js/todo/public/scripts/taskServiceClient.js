var TaskServiceClient = function(serviceUrl) {
  this.serviceUrl = serviceUrl;
};

TaskServiceClient.prototype.getTasks = function(callback) {
  $.get(this.serviceUrl, callback);
};
