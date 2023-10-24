import  { useState } from 'react';
import { AutoComplete } from "primereact/autocomplete";
import axios from 'axios';
import '../style/autoSearch.css';

function AutoSuggestion() {
    const [value, setValue] = useState('');
    const [items, setItems] = useState([]);
    const [noProductsMessage, setNoProductsMessage] = useState('');

    const search = async (event) => {
        const partialProductName = event.query;
        try {
            const response = await axios.get(`http://localhost:9191/api/product/auto/search/${partialProductName}`);
            const suggestions = response.data;

            if (suggestions.length === 0) {
                setNoProductsMessage('No products available');
            } else {
                setNoProductsMessage('');
            }

            setItems(suggestions);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className="autosearch ">
            <h1>Search Product</h1>
        
            <AutoComplete value={value} suggestions={items} completeMethod={search} onChange={(e) => setValue(e.value)} />
            {noProductsMessage && <p>{noProductsMessage}</p>}
        </div>
    );
}

export default AutoSuggestion;

