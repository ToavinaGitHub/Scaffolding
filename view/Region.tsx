import React, { useState, useEffect } from "react";
import { IonButton, IonModal } from '@ionic/react';

function  Region() {
  const url = 'http://localhost:8080/';

  const [loading, setLoading] = useState(true);

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const [show2, setShow2] = useState(false);

  const handleClose2 = () => setShow2(false);
  const handleShow2 = () => setShow2(true);

  const [error, setError] = useState(null);
  const [selectedItem, setSelectedItem] = useState(null);
  const handleSelectItem = (itemKey : any) => {
    handleShow2();
    const itemDetails = region.find((item: { idRegion: any; }) => item.idRegion === itemKey);
    setSelectedItem(itemDetails);
  };

 	const [region, setRegion] = useState([]);
	
	

  //////////// SAVE
  const handleSaveSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data = {};

    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id: value };
      } else {
        data[key] = value;
      }
    }

    try {
      const response = await fetch(url + 'region', {
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
    } catch (error) {
      console.log('Error:', error);
    }
  };

  //////////// UPDATE
  const handleUpdateSubmit = async (event : any) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data = {};
    for (let [key, value] of formData.entries()) {
      if (form.elements[key].tagName === 'SELECT') {
        data[key] = { id: value };
      } else {
        data[key] = value;
      }
    }
    try {
      const response = await fetch(url + 'region', {
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
      const response = await fetch(url + 'region', {
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

    	const handleInputNomRegionChange = (event :any) => {
		setSelectedItem({ ...selectedItem, nomRegion: event.target.value });
	};
	
	const handleInputIdRegionChange = (event :any) => {
		setSelectedItem({ ...selectedItem, idRegion: event.target.value });
	};
	
	

    	useEffect(() => {
		const getRegion = async () => {
			try {
				const response = await fetch(url + 'region');
					if (!response.ok) {
						throw new Error('Network response was not ok');
					};
				const data = await response.json();
				setRegion(data);
			} catch (error) {
				setError(error);
			} finally {
				setLoading(false);
			}
		};
		getRegion();
	}, []);
	
  return (
    <>
      <div className="container">
        <div className="row justify-content-end">
          <div className="col">
            <div className="row">
              <IonButton onClick={handleShow}>
                Add Region
              </IonButton>
            </div>

            <IonModal isOpen={show} onDidDismiss={handleClose}>
              <form action="" method="" id="insert" onSubmit={handleSaveSubmit}>
                	<div className="mb-3"> 
	 	<label className="form-label">Nom Region</label> 
	 	<input className="form-control" type="text" name="nomRegion" />
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
                			<th> Nom Region </th>
			<th> Id Region </th>

                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-body">
              {region.map((item :any) => (
                <tr key={item.idRegion}>
                  		<td>{item.nomRegion}</td>
		<td>{item.idRegion}</td>

                  <td>
                    <IonButton onClick={() => handleDeleteClick(item)}>
                      Delete
                    </IonButton>
                  </td>
                  <td>
                    <IonButton onClick={() => handleSelectItem(item.idRegion)}>
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
	 	<label className="form-label">Nom Region</label> 
	 	<input className="form-control" type="text" name="nomRegion" onChange={handleInputNomRegionChange} value={selectedItem ? selectedItem.nomRegion:''} />
	</div>
	<div className="mb-3"> 
	 	<label className="form-label"></label> 
	 	<input className="form-control" type="hidden" name="idRegion" onChange={handleInputIdRegionChange} value={selectedItem ? selectedItem.idRegion:''} />
	</div>
	
              <IonButton type="submit">
                Save Changes
              </IonButton>
            </form>
          </IonModal>
        </div>
      </div>
    </>
  )
}

export default  Region;
