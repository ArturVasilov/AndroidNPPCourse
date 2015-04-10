<?php

require_once('api/define.php');
require_once('api/ApiBlog.php');

class CommentsManager {

    public function printArticleComments($id) {
        $api = new ApiBlog();
        $params = json_decode('{}');
        $params->token = API_PASS;
        $params->id = $id;
        $response = $api->getArticleById($params);

        $comments = $response->comments;
        $users = $response->users;
        $votes = $response->votes;

        foreach ($comments as $array) {
            echo $array[2] . "\n";
        }
    }

}