package com.projects.rest
import static org.junit.Assert.*
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class testTypeResource {
	String BASE_URL='http://127.0.0.1:1313/ProjectAdminMng/rest'
	String URL_MODULE=BASE_URL+'/typeResource'

	def IN_DATA=JsonOutput.toJson([
		description:'COMPUTADORAS',
		name:'RES_PCS'
	])

	@Test
	void test_1_add(){
		println 'AGREGANDO REGISTRO'
		def path = new HTTPBuilder(URL_MODULE+'/add');

		path.request(Method.PUT, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			body=IN_DATA

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()

				assertTrue resp.status == 201
				assertTrue reader.messages[0].code == 0
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}
	}

	@Test
	void test_2_get(){
		println 'OBTENIENDO REGISTRO'
		def path = new HTTPBuilder(URL_MODULE+'/getAll');
		println URL_MODULE+'/getAll'
		path.request(Method.GET, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()

				assertTrue resp.status == 200
				assertTrue reader.data.findAll{ it.name == jsonSlurper.parseText(IN_DATA).name }.size()>0
				return reader.data.findAll{ it.name == jsonSlurper.parseText(IN_DATA).name }[0].id
			}

			response.failure = { resp ->
				println resp.getStatusLine()
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}
	}

	@Test
	void test_3_update(){
		println 'ACTUALIZANDO REGISTRO'
		def TEMP_ID = getItemId()
		def path = new HTTPBuilder(URL_MODULE+'/update/'+TEMP_ID);

		path.request(Method.POST, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			body=IN_DATA

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()

				assertTrue resp.status == 201
				assertTrue reader.messages[0].code == 0
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}
	}

	@Test
	void test_4_delete(){
		println 'BORRANDO REGISTRO'
		def TEMP_ID = getItemId()
		def path = new HTTPBuilder(URL_MODULE+'/delete/'+TEMP_ID);

		path.request(Method.DELETE, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()

				assertTrue resp.status == 200
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertFalse()
			}
		}
	}

	Integer getItemId(){
		def pathGet = new HTTPBuilder(URL_MODULE+'/getAll');

		def TEMP_ID = pathGet.request(Method.GET, ContentType.JSON) { req ->
			headers.'Content-Type' = 'application/json'
			headers.Accept = 'application/json';

			response.success = { resp, reader ->
				def jsonSlurper = new JsonSlurper()

				assertTrue resp.status == 200
				assertTrue reader.data.findAll{ it.name == jsonSlurper.parseText(IN_DATA).name }.size()>0
				return reader.data.findAll{ it.name == jsonSlurper.parseText(IN_DATA).name }[0].id
			}

			response.failure = { resp ->
				println 'Request failed with status ${resp.status}'
				assertTrue(false)
			}
		}
	}
}
