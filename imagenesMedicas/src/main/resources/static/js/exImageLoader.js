(function (cs) {

    "use strict";

    // Study: Doe, Jane
    //    CT: CT SlicesCT2
    //   PET: PET NAC 2DPT2

    let imagenesId = [];
    let imagenesIdSiguientes = [];
	let longitudMaximaActualAux;

    var ct230Base64 = $("#base64").val();
    var sin = ct230Base64.split('[');
    var sinSon = sin[1].split(']');
	var parsedBase64Imagenes = sinSon[0].split(",");
	addParsedBase64Imagenes(parsedBase64Imagenes);
    var ct230Base642 = $("#base642").val();
    console.log(ct230Base642);
    if (ct230Base642 !== ""){
    	console.log("ct230Base642 !== ");
		var sin2;
	    var sinSon2;
	    var parsedCompleto;
		sin2 = ct230Base642.split('[');
	    sinSon2 = sin2[1].split(']');
	    var parsedBase64Siguiente = sinSon2[0].split(",");
	    addParsedBase64ImagenesSiguientes(parsedBase64Siguiente);
    }

    //access like and array

    function pixelData2str(buf) {
        var bufView = new Uint16Array(buf);
        var len = bufView.length;
        var str = '';

        for(var i = 0; i < len; i++) {
            var word = bufView[i];
            var lower = word & 0xFF;
            var upper = (word >> 8) & 0xFF;
            str += String.fromCharCode(lower) + String.fromCharCode(upper);
        }

        return str;
    }

    function encodePixelData(pixelData) {
        var pixelDataAsString = pixelData2str(pixelData);
        var base64str = window.btoa(pixelDataAsString);
        return base64str;
    }

    function str2ab(str) {
        var buf = new ArrayBuffer(str.length*2); // 2 bytes for each char
        var bufView = new Uint16Array(buf);
        var index = 0;
        for (var i=0, strLen=str.length; i<strLen; i+=2) {
            var lower = str.charCodeAt(i);
            var upper = str.charCodeAt(i+1);
            bufView[index] = (upper << 8) + lower;
            index++;
        }
        return bufView;
    }

    function getPixelData(base64PixelData)
    {
        var pixelDataAsString = window.atob(base64PixelData);
        var pixelData = str2ab(pixelDataAsString);
        return pixelData;
    }

    function addParsedBase64Imagenes(parsedBase64Imagenes){
    	if(parsedBase64Imagenes) {
	        parsedBase64Imagenes.forEach(function (elemento, indice, array) {
	    	    var dataSet = dicomParser.parseDicom(Uint8Array.from(atob(elemento), c => c.charCodeAt(0)));
	    	    // get the pixel data element (contains the offset and length of the data)
	    	    var pixelDataElement = dataSet.elements.x7fe00010;
	    	    // create a typed array on the pixel data (this example assumes 16 bit unsigned data)
	    	    var pixelData = new Uint16Array(dataSet.byteArray.buffer, pixelDataElement.dataOffset, pixelDataElement.length / 2);
	    	    if (indice == (parsedBase64Imagenes.length - 1)){
	        	    var insNumber = dataSet.string('x00200013');
	        	    console.log(insNumber);
	        	    longitudMaximaActualAux = parseInt(insNumber);  	    	    	
	    	    }
	    	    imagenesId.push(pixelData);
	        });
	        // create a typed array on the pixel data (this example assumes 16 bit unsigned data)
    	}
    }
    
    function addParsedBase64ImagenesSiguientes(parsedBase64Imagenes){
    	if(parsedBase64Imagenes) {
	        parsedBase64Imagenes.forEach(function (elemento, indice, array) {
	    	    var dataSet = dicomParser.parseDicom(Uint8Array.from(atob(elemento), c => c.charCodeAt(0)));
	    	    // get the pixel data element (contains the offset and length of the data)
	    	    var pixelDataElement = dataSet.elements.x7fe00010;
	    	    // create a typed array on the pixel data (this example assumes 16 bit unsigned data)
	    	    var pixelData = new Uint16Array(dataSet.byteArray.buffer, pixelDataElement.dataOffset, pixelDataElement.length / 2);    	    
	    	    imagenesIdSiguientes.push(pixelData);
	        });
    	}
        console.log("cargo las imagesSiguientes");
    }

    function getCTImage(imageId) {

        var width = 512;
        var height = 512;
        
        function getPixelData()
        {
  	
        	var lastChar = imageId.substring(17,imageId.length)
    		var indiceString = "instanceNumber://" + lastChar;
    		var indice = parseInt(lastChar);
            if(imageId === indiceString)
            {	
            	if ( indice < longitudMaximaActualAux ){
	            	console.log("Buscamos a... ", indiceString);
	            	var unit16Array =  imagenesId[indice];
	            	return unit16Array;
            	} else {
	            	console.log("Buscamos a...en el otro lado ", indice - longitudMaximaActualAux);
	            	var unit16Array =  imagenesIdSiguientes[indice - longitudMaximaActualAux];
	            	return unit16Array;            		
            	}
            }
        }
        function getImageData() {
            return imageData;
        }
        
        var image = {               
                imageId: imageId,
                minPixelValue : 0,
                maxPixelValue : 4096,
                slope: 1.0,
                intercept : -1024,
                windowCenter : 40,
                windowWidth : 400,
                render: cornerstone.renderGrayscaleImage,
                getPixelData: getPixelData,                
                rows: 512,
                columns: 512,
                height: height,
                width: width,
                color: false,
                columnPixelSpacing: 1,
                rowPixelSpacing: 1,
                sizeInBytes: width * height * 4
        };

        return {
            promise: new Promise((resolve) => {
              resolve(image);
            }),
            cancelFn: undefined
        };
    }


    // register our imageLoader plugin with cornerstone
    
    cs.registerImageLoader('instanceNumber', getCTImage);
}(cornerstone));