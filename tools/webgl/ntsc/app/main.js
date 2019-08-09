function createShader(gl, type, source) {
    var shader = gl.createShader(type);
    gl.shaderSource(shader, source);
    gl.compileShader(shader);
    var success = gl.getShaderParameter(shader, gl.COMPILE_STATUS);
    if (success) {
        return shader;
    }

    console.log(gl.getShaderInfoLog(shader));
    gl.deleteShader(shader);
}

function createProgram(gl, vertexShader, fragmentShader) {
    var program = gl.createProgram();
    gl.attachShader(program, vertexShader);
    gl.attachShader(program, fragmentShader);
    gl.linkProgram(program);
    var success = gl.getProgramParameter(program, gl.LINK_STATUS);
    if (success) {
        return program;
    }
    
    console.log(gl.getProgramInfoLog(program));
    gl.deleteProgram(program);
}

function setRectangle(gl, x, y, width, height) {
    var x1 = x;
    var x2 = x + width;
    var y1 = y;
    var y2 = y + height;
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array([
       x1, y1,
       x2, y1,
       x1, y2,
       x1, y2,
       x2, y1,
       x2, y2,
    ]), gl.STATIC_DRAW);
  }

  function createAndSetupTexture(gl) {
    var texture = gl.createTexture();
    gl.bindTexture(gl.TEXTURE_2D, texture);
 
    // Set up texture so we can render any size image and so we are
    // working with pixels.
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_S, gl.CLAMP_TO_EDGE);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_WRAP_T, gl.CLAMP_TO_EDGE);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.NEAREST);
    gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MAG_FILTER, gl.NEAREST);
 
    return texture;
  }

  function runProgram(gl, program, image) {
    // Supply data
    var positionLocation = gl.getAttribLocation(program, "a_position");
    var texcoordLocation = gl.getAttribLocation(program, "a_texCoord");

    var positionBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, positionBuffer);
    setRectangle(gl, 0, 0, image.width, image.height);

    var texcoordBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, texcoordBuffer);
    setRectangle(gl, 0, 0, 1.0, 1.0);

    var originalImageTexture = createAndSetupTexture(gl);
    gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, image);

    // lookup uniforms
    var resolutionLocation = gl.getUniformLocation(program, "u_resolution");

    gl.viewport(0, 0, gl.canvas.width, gl.canvas.height);
    gl.clearColor(0, 0, 0, 0);
    gl.clear(gl.COLOR_BUFFER_BIT);

    gl.useProgram(program);

    gl.enableVertexAttribArray(positionLocation);
    gl.bindBuffer(gl.ARRAY_BUFFER, positionBuffer);
    var size = 2;          // 2 components per iteration
    var type = gl.FLOAT;   // the data is 32bit floats
    var normalize = false; // don't normalize the data
    var stride = 0;        // 0 = move forward size * sizeof(type) each iteration to get the next position
    var offset = 0;        // start at the beginning of the buffer
    gl.vertexAttribPointer(positionLocation, size, type, normalize, stride, offset)

    gl.enableVertexAttribArray(texcoordLocation);
    gl.bindBuffer(gl.ARRAY_BUFFER, texcoordBuffer);
    var size = 2;          // 2 components per iteration
    var type = gl.FLOAT;   // the data is 32bit floats
    var normalize = false; // don't normalize the data
    var stride = 0;        // 0 = move forward size * sizeof(type) each iteration to get the next position
    var offset = 0;        // start at the beginning of the buffer
    gl.vertexAttribPointer(texcoordLocation, size, type, normalize, stride, offset)

    // gl.uniform2f(resolutionLocation, image.width, image.height);
    gl.uniform2f(resolutionLocation, gl.canvas.width, gl.canvas.height);

    gl.drawArrays(gl.TRIANGLES, 0, 6);

  }

define(["text!app/vertex.fx", "text!app/fragment.fx", "text!app/fragment-2nd.fx", "text!app/fragment-3rd.fx", "text!app/fragmentSinglePass.fx"],
function (vertexShaderSource, pass1fragmentShaderSource, pass2fragmentShaderSource, hyllianFragmentShaderSource, fragmentSinglePassShaderSource) {
    var image = new Image();
    // image.src = "http://localhost:8000/sample.jpg";
    image.src = "http://localhost:8000/JACKAL2-SEM.png";
    image.onload = function() {
        var canvas = document.getElementById("c");
        canvas.width = image.width;
        canvas.height = image.height;
        var gl = canvas.getContext("webgl");

        // Setup shaders
        var vertexShader = createShader(gl, gl.VERTEX_SHADER, vertexShaderSource);
        var pass1FragmentShader = createShader(gl, gl.FRAGMENT_SHADER, pass1fragmentShaderSource);
        var pass2FragmentShader = createShader(gl, gl.FRAGMENT_SHADER, pass2fragmentShaderSource);
        var fragmentSinglePassShader = createShader(gl, gl.FRAGMENT_SHADER, fragmentSinglePassShaderSource);
        var hyllianFragmentShader = createShader(gl, gl.FRAGMENT_SHADER, hyllianFragmentShaderSource);

        var pass1Program = createProgram(gl, vertexShader, pass1FragmentShader);
        var pass2Program = createProgram(gl, vertexShader, pass2FragmentShader);
        var singlePassProgram = createProgram(gl, vertexShader, fragmentSinglePassShader);
        var hyllianProgram = createProgram(gl, vertexShader, hyllianFragmentShader);

        // runProgram(gl, hyllianProgram, image);
        runProgram(gl, singlePassProgram, image);
        // runProgram(gl, pass2Program, canvas);
        // runProgram(gl, hyllianProgram, canvas);
    };
});