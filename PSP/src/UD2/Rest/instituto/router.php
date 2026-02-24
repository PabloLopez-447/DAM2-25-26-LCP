<?php
class Router
{
    private $rutas = [];
    public function get($path, $callback)
    {
        $this->rutas['GET'][$path] = $callback;
    }
    public function post($path, $callback)
    {
        $this->rutas['POST'][$path] = $callback;
    }
    public function put($path, $callback)
    {
        $this->rutas['PUT'][$path] = $callback;
    }
    public function patch($path, $callback)
    {
        $this->rutas['PATCH'][$path] = $callback;
    }
    public function delete($path, $callback)
    {
        $this->rutas['DELETE'][$path] = $callback;
    }
    public function dispatch($method, $path)
    {
        foreach ($this->rutas[$method] as $route => $callback)
            if (preg_match("@^$route$@", $path, $params)) {
                $respuesta = call_user_func($callback, $params);
                $statusCode = $respuesta['status'] ?? 200;
                http_response_code($statusCode);
                echo $respuesta['body'] ?? '';
                return;
            }
        http_response_code(400);
        echo json_encode(['message' => 'Bad Request']);
    }
}
?>