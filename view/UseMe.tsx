import React, { useState, useEffect } from "react";
import { IonButton, IonModal,IonPage } from '@ionic/react';

const  UseMe: React.FC = () => {
  const url = 'http://localhost:8080/';

  const [loading, setLoading] = useState(true);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [show2, setShow2] = useState(false);

  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => setShow2(true);

  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] =  useState<any>(null);
  const handleSelectItem = (itemKey : any) => {
    handleShow2();
    const itemDetails = use_me.find((item: { id: any; }) => item.id === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [use_me, setUse_me] = useState([]);
	
	const [useYou, setUseYou] = useState([]);
	
	

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 3; 
  const totalPageCount = Math.ceil(use_me.length / itemsPerPage);

  //////////// SAVE
  const handleSaveSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data :any = {};

    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id: value };
      } else {
        data[key] = value;
      }
    }

    try {
      const response = await fetch(url + 'use_me', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      handleClose();
      // If you want to reload the page after success
      window.location.reload();
    } catch (error : any) {
      console.log('Error:', error);
    }
  };

  //////////// UPDATE
  const handleUpdateSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data :any = {};
    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id : value };
      } else {
        data[key] = value;
      }
    }
    try {
      const response = await fetch(url + 'use_me', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      handleClose2();
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

  //////////// DELETE
  const handleDeleteClick = async (item : any) => {
    try {
      console.log(item);
      const response = await fetch(url + 'use_me', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(item)
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // If you want to reload the page after success
      window.location.reload();
    } catch (error) {
      console.error('Error:', error);
    }
  };

    	const handleInputIdChange = (event :any) => {
		setSelectedItem({ ...selectedItem, id: event.target.value });
	};
	
	const handleInputNomBChange = (event :any) => {
		setSelectedItem({ ...selectedItem, nomB: event.target.value });
	};
	
	const handleSelectUseYouChange = (event : any) => {
		setSelectedItem({ ...selectedItem, useYou: event.target.value });
	};
	
	

    	useEffect(() => {
		const getUse_me = async () => {
			try {
				const response = await fetch(url + 'use_me?page='+ (currentPage - 1));
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setUse_me(data.content);
			} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getUse_me();
	}, [currentPage]);
	useEffect(() => {
		const getUseYou = async () => {
			try {
				const response = await fetch(url + 'useYou');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setUseYou(data);
			} catch (error :any) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getUseYou();
	}, []);
	
  return (
    <IonPage>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add UseMe
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Nom B</label> 
	 	<input className="form-control" type="text" name="nomB" />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">id</label> 
	 	<select className="form-control" name="useYou" id="select-useYou">
			{useYou.map((elt :any) => (
				<option value={elt.id}>{elt.nomA}</option>
			))}
			
		</select>
	</div>
                <IonButton type="submit" >
                  Save Changes
                </IonButton>
              </form>
            </IonModal>
          </div>
        </div>
        <div className="row">
          <table className="table">
            <thead id="table-head">
              <tr>
                			<th> Id </th>
			<th> Nom B </th>
			<th> Id Use You </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {use_me.map((item :any) => (
                <tr key={item.id}>
                  		<td>{item.id}</td>
		<td>{item.nomB}</td>
		<td>{item.useYou.nomA}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.id)}>
                      Update
                    </IonButton>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <IonModal isOpen={show2} onDidDismiss={handleClose2}>
            <form action="" method="" id="update" onSubmit={handleUpdateSubmit}>
              	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="id" onChange={handleInputIdChange} value={selectedItem ? selectedItem.id:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">Nom B</label> 
	 	<input className="form-control" type="text" name="nomB" onChange={handleInputNomBChange} value={selectedItem ? selectedItem.nomB:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label">id</label> 
	 	<select className="form-control" name="useYou">
			{useYou.map((elt : any) => (
		<option value={elt.id}>{elt.nomA}</option>
	))}
	
	
	</select>
	</div>
              <IonButton type="submit">
                Save Changes
              </IonButton>
            </form>
          </IonModal>
        </div>
        {/* Pagination */ }
        <nav aria-label="Page navigation">
        <ul className="pagination justify-content-center">
          <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage - 1)} disabled={currentPage === 1}>Previous</button>
          </li>
          {[...Array(totalPageCount+1)].map((_, index) => (
            <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
              <button className="page-link" onClick={() => setCurrentPage(index + 1)}>{index + 1}</button>
            </li>
          ))}
          <li className={`page-item ${currentPage === totalPageCount+1 ? 'disabled' : ''}`}>
            <button className="page-link" onClick={() => setCurrentPage(prevPage => prevPage + 1)} disabled={currentPage === totalPageCount+1}>Next</button>
          </li>
        </ul>
      </nav>
      </div>
    </IonPage>
  )
}

export default  UseMe;
