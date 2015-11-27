var TaskServiceClient = function(serviceUrl) {
  this.serviceUrl = serviceUrl;
};

TaskServiceClient.prototype.getTasks = function(callback) {
  $.get(this.serviceUrl, callback);
};

TaskServiceClient.prototype.deleteTask = function(taskId, callback) {
  $.ajax({
    url: this.serviceUrl + "/" + taskId,
    type: 'DELETE',
    success: callback});
};
