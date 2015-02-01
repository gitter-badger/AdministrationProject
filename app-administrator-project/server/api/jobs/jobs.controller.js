'use strict';

var _ = require('lodash');
var http = require('http');
var module = 'jobController';
var config = require('./../../config/environment');
var formatError = require('./../../components/format');

var underscore = require('underscore');
//logger configuration
var logger = require('./../../config/logger.js')
var log = logger.logger;

exports.getAll = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/job/getAll',
    method: 'GET'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.add = function(req, result) {

  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/job/add',
    method: 'PUT'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.update = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/job/update',
    method: 'POST'
  });

  formatError.executeRequest(optionServer, req, result);
};

exports.remove = function(req, result) {
  var optionServer = underscore.extend(config.serverRestTemplate, {
    path: '/ProjectAdminMng/rest/job/delete/' + req.params.id,
    method: 'DELETE'
  });

  formatError.executeRequest(optionServer, req, result);
};