(function (cs) {

    "use strict";
    function toDataUrl(url, callback) {

        var xhr = new XMLHttpRequest();
        xhr.onload = function() {
            var reader = new FileReader();
            reader.onloadend = function() {
                callback(reader.result);
            }
            reader.readAsDataURL(xhr.response);
        };
        xhr.open('GET', url);
        xhr.responseType = 'blob';
        xhr.send();
    }
    
    function str2ab(str) {
        var buf = new ArrayBuffer(str.length*2); // 2 bytes for each char
        var bufView = new Uint16Array(buf);
        var index = 0;
        for (var i=0, strLen=str.length; i<strLen; i+=2) {
            var lower = str.charCodeAt(i);
            var upper = str.charCodeAt(i+1);
            bufView[index] = lower + (upper <<8);
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

    var image1PixelData = toDataUrl('file:///imagenesMedicas/DICOM/000020B2/AA0944F6/AA7F2F1E/EE00A915', function(myBase64) {
        console.log(myBase64); // myBase64 is the base64 string
    });
    var image2PixelData = toDataUrl('file:///imagenesMedicas/DICOM/000020B2/AA0944F6/AA7F2F1E/EE00D988', function(myBase64) {
        console.log(myBase64); // myBase64 is the base64 string
    });

    function getExampleImage(imageId) {
        const width = 256;
        const height = 256;
        function getPixelData () {
            if(imageId === 'example://1'
                return image1PixelData;
            } else if (imageId === 'example://2') {
                return image2PixelData;
            }

            throw "unknown imageId";
        }

        var image = {
            imageId: imageId,
            minPixelValue : 0,
            maxPixelValue : 257,
            slope: 1.0,
            intercept: 0,
            windowCenter : 127,
            windowWidth : 256,
            getPixelData: getPixelData,
            rows: height,
            columns: width,
            height: height,
            width: width,
            color: false,
            columnPixelSpacing: .8984375,
            rowPixelSpacing: .8984375,
            sizeInBytes: width * height * 2
        };

        return {
            promise: new Promise((resolve) => {
              resolve(image);
            }),
            cancelFn: undefined
        };
    }


    // register our imageLoader plugin with cornerstone
    cs.registerImageLoader('example', getExampleImage);

}(cornerstone));