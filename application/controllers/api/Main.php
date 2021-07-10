<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Main extends BD_Controller {
    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        $this->auth();
        $this->load->model('Crud');
    }
	
	public function test_post()
	{
       
        $theCredential = $this->user_data;
        $this->response($theCredential, 200); // OK (200) being the HTTP response code
        
	}

    public function users_get()
    {

        $id = $this->get('id');


        if ($id === NULL)
        {
            $getUser = $this->Crud->readData('id,name, username,password, role','user')->result();
            if ($getUser)
            {
                // Set the response and exit
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUser
                ];
                $this->response($output, REST_Controller::HTTP_OK); // OK (200) being the HTTP response code
            }
            else
            {
                // Set the response and exit
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'No nota were found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); // NOT_FOUND (404) being the HTTP response code
            }
        }

        if($id){
            $where = [
                'id'=> $id
            ];
            $getUserById = $this->Crud->readData('id,name, username,password, role','user',$where)->result();

            if($getUserById){
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUserById
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Get Nota or id Not Found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }

    public function users_put(){

        $id = (int) $this->get('id');
        if($id){
            $where = [
                'id'=> $id
            ];
            $cekId = $this->Crud->readData('id','user',$where)->num_rows();

            if($cekId > 0){
                $data = [
                    "name"      => $this->put('name'),
                    "username"  => $this->put('username'),
                    "password"  => sha1($this->put('password')),
                    "role"      => $this->put('role')
                ];

                $updateData = $this->Crud->updateData('user',$data,$where);
                if($updateData){
                    $output = [
                        'status' => 200,
                        'error' => false,
                        'message' => 'Success edit user',
                    ];
                    $this->response($output, REST_Controller::HTTP_OK);
                }else{
                    $output = [
                        'status' => 400,
                        'error' => false,
                        'message' => 'Failed edit user',
                    ];
                    $this->response($output, REST_Controller::HTTP_BAD_REQUEST); 
                }
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed delete user or id not found',
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }

    }

    public function users_delete()
    {

        $id = (int) $this->get('id');

        if($id){
            $where = [
                'id'=> $id
            ];
            $cekId = $this->Crud->readData('id','user',$where)->num_rows();

            if($cekId > 0){
                
                $this->Crud->deleteData('user',$where);
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success delete user',
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed delete user or id not found',
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }

    public function nota_post() {
     
        $data = [
            "approve"=>'true'
            
        ];

        $createnota = $this->Crud->createData('penjualan',$data);
        
        if($createnota) {
            $output = [
                'status' => 200,
                'error' => false,
                'message' => 'Success create nota',
                'data' => $data
            ];
            $this->set_response($output, REST_Controller::HTTP_OK);
        }else{
            $output = [
                'status' =>400,
                'error' => false,
                'message' => 'Failed create nota',
                'data'=> []
            ];
            $this->set_response($output, REST_Controller::HTTP_BAD_REQUEST);
        }
    }
    public function cupang_post() {
        $nama = $this->post('nama_cupang');
        $harga = $this->post('harga_cupang');
        $gambar = $this->post('gambar_cupang');

        $data = [
            "nama_cupang"=>$nama,
            "harga_cupang"=>$harga,
            "gambar_cupang"=>$gambar
        ];

        $createcupang = $this->Crud->createData('cupang',$data);
        
        if($createcupang) {
            $output = [
                'status' => 200,
                'error' => false,
                'message' => 'Success create nota',
                'data' => $data
            ];
            $this->set_response($output, REST_Controller::HTTP_OK);
        }else{
            $output = [
                'status' =>400,
                'error' => false,
                'message' => 'Failed create nota',
                'data'=> []
            ];
            $this->set_response($output, REST_Controller::HTTP_BAD_REQUEST);
        }
    }

    public function nota_get()
    {

        $idCupang = $this->get('id_cupang');
        $id = $this->get('id_cupang');


        if ($id === NULL)
        {
            $where = [
                'approve'=> 'true'
            ];
            $getUser = $this->db->query('SELECT penjualan.id_penjualan,user.name,cupang.nama_cupang,penjualan.nama_pembeli,penjualan.notelp_pembeli,penjualan.alamat_pembeli, penjualan.harga_penjualan, penjualan.date FROM penjualan INNER JOIN user ON user.id = penjualan.id_user INNER JOIN cupang ON cupang.id_cupang = penjualan.id_cupang')->result();
            
            if ($getUser)
            {   
                // Set the response and exit
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUser
                    
                ];
                $this->response($output, REST_Controller::HTTP_OK); // OK (200) being the HTTP response code
            }
            else
            {
                // Set the response and exit
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'No nota were found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); // NOT_FOUND (404) being the HTTP response code
            }
        }
        if($id_cupang){
            $where = [
                'id_cupang'=> $id_cupang
            ];
            $getUser = $this->Crud->readData('nama_cupang','cupang',$where)->result();
            if($getUser){
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUser
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Get Nota or id Not Found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
        if($id){
            $where = [
                'id'=> $id
            ];
            $getUserById = $this->Crud->readData('id_penjualan,id_user,id_cupang,nama_pembeli,notelp_pembeli,alamat_pembeli, harga_penjualan,date','penjualan',$where)->result();
            if($getUserById){
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUserById
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Get Nota or id Not Found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }
    public function dbhome_get()
    {

        $where = [
            'approve'=> 'false'
        ];
       
        
            $getUser = $this->Crud->readData('id_penjualan,id_user,id_cupang,nama_pembeli,notelp_pembeli,alamat_pembeli, harga_penjualan,date','penjualan', $where)->result();     
            
            if ($getUser)
            {   
                // Set the response and exit
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUser
                    
                ];
                $this->response($output, REST_Controller::HTTP_OK); // OK (200) being the HTTP response code
            }
            else
            {
                // Set the response and exit
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'No nota were found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); // NOT_FOUND (404) being the HTTP response code
            }
            
        
        
    }
    
    public function cupang_get()
    {

        $id = $this->get('id_cupang');


        if ($id === NULL)
        {
            $getCupang = $this->Crud->readData('id_cupang,nama_cupang,harga_cupang, gambar_cupang','cupang')->result();
            if ($getCupang)
            {
                // Set the response and exit
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getCupang
                ];
                $this->response($output, REST_Controller::HTTP_OK); // OK (200) being the HTTP response code
            }
            else
            {
                // Set the response and exit
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'No nota were found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); // NOT_FOUND (404) being the HTTP response code
            }
        }

        if($id){
            $where = [
                'id_cupang'=> $id
            ];
            $getUserById = $this->Crud->readData('id_cupang,nama_cupang,harga_cupang, gambar_cupang','cupang',$where)->result();

            if($getUserById){
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Get Nota',
                    'data'=> $getUserById
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Get Nota or id Not Found',
                    'data'=> []
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }

    public function nota_put()
    {
        $id = (int) $this->get('id');
        if($id){
            $where = [
                'id_penjualan'=> $id
            ];
            $cekId = $this->Crud->readData('id_penjualan','penjualan',$where)->num_rows();

            if($cekId > 0){
                $data = [
                    "approve"  => "true"
                ];

                $updateData = $this->Crud->updateData('penjualan',$data,$where);
                if($updateData){
                    $output = [
                        'status' => 200,
                        'error' => false,
                        'message' => 'Success Edit Nota',
                    ];
                    $this->response($output, REST_Controller::HTTP_OK);
                }else{
                    $output = [
                        'status' => 400,
                        'error' => false,
                        'message' => 'Failed Edit Nota',
                    ];
                    $this->response($output, REST_Controller::HTTP_BAD_REQUEST); 
                }
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Delete Nota or Id Not Found',
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }
    public function cupang_put()
    {
        $id = (int) $this->get('id_cupang');
        if($id){
            $where = [
                'id_cupang'=> $id
            ];
            $cekId = $this->Crud->readData('id_cupang','cupang',$where)->num_rows();

            if($cekId > 0){
                $data = [
                    "nama_cupang"     => $this->put('nama_cupang'),
                    "harga_cupang"    => $this->put('harga_cupang'),
                    "gambar_cupang"    => $this->put('gambar_cupang')
                ];

                $updateData = $this->Crud->updateData('cupang',$data,$where);
                if($updateData){
                    $output = [
                        'status' => 200,
                        'error' => false,
                        'message' => 'Success Edit Nota',
                    ];
                    $this->response($output, REST_Controller::HTTP_OK);
                }else{
                    $output = [
                        'status' => 400,
                        'error' => false,
                        'message' => 'Failed Edit Nota',
                    ];
                    $this->response($output, REST_Controller::HTTP_BAD_REQUEST); 
                }
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Delete Nota or Id Not Found',
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }

    public function nota_delete()
    {

        $id = (int) $this->get('id');

        if($id){
            $where = [
                'id_penjualan'=> $id
            ];
            $cekId = $this->Crud->readData('id_penjualan','penjualan',$where)->num_rows();

            if($cekId > 0){
                
                $this->Crud->deleteData('penjualan',$where);
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Delete Nota',
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Delete User or Id Not Found',
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }
    public function cupang_delete()
    {

        $id = (int) $this->get('id_cupang');

        if($id){
            $where = [
                'id_cupang'=> $id
            ];
            $cekId = $this->Crud->readData('id_cupang','cupang',$where)->num_rows();

            if($cekId > 0){
                
                $this->Crud->deleteData('cupang',$where);
                $output = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Success Delete cupang',
                ];
                $this->response($output, REST_Controller::HTTP_OK);
            }else{
                $output = [
                    'status' => 404,
                    'error' => false,
                    'message' => 'Failed Delete User or Id Not Found',
                ];
                $this->response($output, REST_Controller::HTTP_NOT_FOUND); 
            }
        }
    }

}
